docker stop $IMAGE
docker rm $IMAGE
docker run -d --rm --name $IMAGE -p 8950:8950 --net=host 192.168.1.55:5000/$IMAGE
