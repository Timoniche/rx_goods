package ddulaev.rxgoods.dao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Currency {
    RUB(1.0),
    EUR(0.011),
    USD(0.014);

    private final double cost;

    public static double convertTo(double val, Currency from, Currency to) {
        return val * to.cost / from.cost;
    }
}
