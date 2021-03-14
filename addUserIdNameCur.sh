id=$1
name=$2
currency=$3
curl -X POST "localhost:8080/addUser?id=${id}&name=${name}&currency=${currency}"
