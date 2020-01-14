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

#  --data '{"id": "normal","name": "Normal User","type": "WORKFLOW"}'

# routeGuidance / Kaspar Riese
# creat group
echo "create groupe  routeGuidance"
curl --request POST \
  --url http://localhost:8080/engine-rest/group/create \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Content-Type: application/json' \
  --data '{"id": "routeGuidance","name": "Route Guidance","type": "WORKFLOW"}'
  echo "create groupe  routeGuidance authorization"
  # create authorization
curl --request POST \
  --url http://localhost:8080/engine-rest/authorization/create \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Content-Type: application/json' \
  --data '{"type": 1,"permissions": ["ALL"],"userId": null,"groupId": "routeGuidance","resourceType": 0,"resourceId": "tasklist"}'
  echo "create User Kasper"
# create User Kaspar
curl --request POST \
  --url http://localhost:8080/engine-rest/user/create \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Content-Type: application/json' \
  --data '{"profile": {"id": "kaspar","firstName": "Kaspar","lastName": "Riese","email": "riese@mod.org"},"credentials": {"password": "kaspar"}}'
echo "Add Kaspar to groupe routeGuidance"
# add Kaspar to groupe Normal
curl --request PUT \
  --url http://localhost:8080/engine-rest/group/routeGuidance/members/kaspar \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Host: localhost:8080'


# finance / Corinna Pfaff
# creat group
echo "create groupe  finance"
curl --request POST \
  --url http://localhost:8080/engine-rest/group/create \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Content-Type: application/json' \
  --data '{"id": "finance","name": "Finance","type": "WORKFLOW"}'
# create authorization
echo "create groupe  finance authorization"
curl --request POST \
  --url http://localhost:8080/engine-rest/authorization/create \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Content-Type: application/json' \
  --data '{"type": 1,"permissions": ["ALL"],"userId": null,"groupId": "finance","resourceType": 0,"resourceId": "tasklist"}'
  # create User Corinna
    echo "create User Corinna"
curl --request POST \
  --url http://localhost:8080/engine-rest/user/create \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Content-Type: application/json' \
  --data '{"profile": {"id": "corinna","firstName": "Corinna","lastName": "Pfaff","email": "pfaff@mod.org"},"credentials": {"password": "corinna"}}'
# add Corinna to groupe finance
echo "Add Pfaff to groupe finance"
curl --request PUT \
  --url http://localhost:8080/engine-rest/group/finance/members/corinna \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Host: localhost:8080'


#customerService / Leonie Hirsch
# creat group
echo "create groupe customerService"
curl --request POST \
  --url http://localhost:8080/engine-rest/group/create \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Content-Type: application/json' \
  --data '{"id": "customerService","name": "Customer Service","type": "WORKFLOW"}'
# create authorization
echo "create groupe  customerService authorization"
curl --request POST \
  --url http://localhost:8080/engine-rest/authorization/create \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Content-Type: application/json' \
  --data '{"type": 1,"permissions": ["ALL"],"userId": null,"groupId": "customerService","resourceType": 0,"resourceId": "tasklist"}'
  # create User Leonie
    echo "create User Leonie"
curl --request POST \
  --url http://localhost:8080/engine-rest/user/create \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Content-Type: application/json' \
  --data '{"profile": {"id": "leonie","firstName": "Leonie","lastName": "Hirsch","email": "hirsch@mod.org"},"credentials": {"password": "leonie"}}'
# add Leonie to groupe customerService
echo "add Leonie to groupe customerService"
curl --request PUT \
  --url http://localhost:8080/engine-rest/group/customerService/members/leonie \
  --header 'Accept: */*' \
  --header 'Cache-Control: no-cache' \
  --header 'Host: localhost:8080'

##########################################Create Customers################################################







# attach back to Camunda
fg