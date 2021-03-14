id=$1
cost_usd=$2
name=$3 # USD, EUR, RUB
curl -X POST "localhost:8080/addProduct?id=${id}&cost_usd=${cost_usd}&name=${name}"
