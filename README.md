# Mobility on Demand
Projekt für IIS


## Using Docker
The docker environment setups followning services:
- Camunda Server
- Active MQ Server

### Dependencies
- Docker

### How to use
Run in main folder:

```docker-compose -f "docker\docker-compose.yml" up -d --build```

<aside class="notice">
The containers will not save datas.
</aside>