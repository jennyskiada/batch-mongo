# Docker Cheat Sheet

## Docker After Windows Sleep
<i>An error occurred trying to connect: Get http://%2F%2F.%2Fpipe%2Fdocker_engine/v1.24/containers/json: open //./pipe/docker_engine: The system cannot find the file specified.</i><br/><br/>
docker-machine stop default<br/>
docker-machine start default<br/>
docker-machine env default<br/>
eval $("C:\Program Files\Docker Toolbox\docker-machine.exe" env default)

## Re-create The Default Machine
docker-machine rm default<br/>
docker-machine create --driver virtualbox default<br/>
eval "$(docker-machine env default)"

## Remove Dangling Images
docker rmi $(docker images -f dangling=true -q)
