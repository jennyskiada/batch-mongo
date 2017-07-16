# Docker Cheat Sheet

## Docker After Windows Sleep

## Re-create The Default Machine
docker-machine rm default<br/>
docker-machine create --driver virtualbox default<br/>
eval "$(docker-machine env default)"

## Remove Dangling Images
docker rmi $(docker images -f dangling=true -q)
