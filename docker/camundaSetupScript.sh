#!/bin/bash

# enabel job control
set -m

# start Camunda
./camunda.sh &

# wait wile camunda is starting

url="http://localhost:8080/engine-rest/version"

until curl --output /dev/null --silent --head --fail "$url"; do
  sleep 1
done

# setup camunda user structure
# creat group
curl --request POST \
  --url http://localhost:8080/engine-rest/group/create \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Content-Type: application/json' \
  --data '{"id": "normal","name": "Normal User","type": "WORKFLOW"}'

# create authorization
curl --request POST \
  --url http://localhost:8080/engine-rest/authorization/create \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Content-Type: application/json' \
  --data '{"type": 1,"permissions": ["ALL"],"userId": null,"groupId": "normal","resourceType": 0,"resourceId": "tasklist"}'

# create User Fozzie
curl --request POST \
  --url http://localhost:8080/engine-rest/user/create \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Content-Type: application/json' \
  --data '{"profile": {"id": "fozzie","firstName": "Fozzie","lastName": "Bear","email": "fozzie@demo.org"},"credentials": {"password": "fozzie"}}'

# create User Kermit
curl --request POST \
  --url http://localhost:8080/engine-rest/user/create \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Content-Type: application/json' \
  --data '{"profile": {"id": "kermit","firstName": "Kermit","lastName": "The Frog","email": "kermit@demo.org"},"credentials": {"password": "kermit"}}'

# add Fozzi to groupe Normal
curl --request PUT \
  --url http://localhost:8080/engine-rest/group/normal/members/fozzie \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Host: localhost:8080'

# add Kermit to groupe camunda-admin
curl --request PUT \
  --url http://localhost:8080/engine-rest/group/camunda-admin/members/kermit \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Host: localhost:8080'


# attach back to Camunda
fg