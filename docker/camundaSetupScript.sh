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

##########################################Create Camunda default Users################################################
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



##########################################Create Camunda Users (MoD)################################################

## generate Project specific User Groups

# route_guidance / Kaspar Riese
# creat group
curl --request POST \
  --url http://localhost:8080/engine-rest/group/create \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Content-Type: application/json' \
  --data '{"id": "route_guidance","name": "Route Guidance","type": "WORKFLOW"}'
  # create authorization
curl --request POST \
  --url http://localhost:8080/engine-rest/authorization/create \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Content-Type: application/json' \
  --data '{"type": 1,"permissions": ["ALL"],"userId": null,"groupId": "route_guidance","resourceType": 0,"resourceId": "tasklist"}'
# create User Kaspar
curl --request POST \
  --url http://localhost:8080/engine-rest/user/create \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Content-Type: application/json' \
  --data '{"profile": {"id": "kaspar","firstName": "Kaspar","lastName": "Riese","email": "riese@mod.org"},"credentials": {"password": "kaspar"}}'
# add Kaspar to groupe Normal
curl --request PUT \
  --url http://localhost:8080/engine-rest/group/route_guidance/members/kaspar \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Host: localhost:8080'


# finance / Corinna Pfaff
# creat group
curl --request POST \
  --url http://localhost:8080/engine-rest/group/create \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Content-Type: application/json' \
  --data '{"id": "finance","name": "Finance","type": "WORKFLOW"}'
# create authorization
curl --request POST \
  --url http://localhost:8080/engine-rest/authorization/create \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Content-Type: application/json' \
  --data '{"type": 1,"permissions": ["ALL"],"userId": null,"groupId": "finance","resourceType": 0,"resourceId": "tasklist"}'
  # create User Corinna
curl --request POST \
  --url http://localhost:8080/engine-rest/user/create \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Content-Type: application/json' \
  --data '{"profile": {"id": "corinna","firstName": "Corinna","lastName": "Pfaff","email": "pfaff@mod.org"},"credentials": {"password": "corinna"}}'
# add Corinna to groupe finance
curl --request PUT \
  --url http://localhost:8080/engine-rest/group/finance/members/corinna \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Host: localhost:8080'


#customer_service / Leonie Hirsch
# creat group
curl --request POST \
  --url http://localhost:8080/engine-rest/group/create \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Content-Type: application/json' \
  --data '{"id": "customer_service","name": "Customer Service","type": "WORKFLOW"}'
# create authorization
curl --request POST \
  --url http://localhost:8080/engine-rest/authorization/create \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Content-Type: application/json' \
  --data '{"type": 1,"permissions": ["ALL"],"userId": null,"groupId": "customer_service","resourceType": 0,"resourceId": "tasklist"}'
  # create User Leonie
curl --request POST \
  --url http://localhost:8080/engine-rest/user/create \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Content-Type: application/json' \
  --data '{"profile": {"id": "leonie","firstName": "Leonie","lastName": "Hirsch","email": "hirsch@mod.org"},"credentials": {"password": "leonie"}}'
# add Leonie to groupe customer_service
curl --request PUT \
  --url http://localhost:8080/engine-rest/group/customer_service/members/leonie \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Host: localhost:8080'

##########################################Create Customers################################################







# attach back to Camunda
fg