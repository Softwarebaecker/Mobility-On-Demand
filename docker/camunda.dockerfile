# author Nil Kuchenbäcker

FROM camunda/camunda-bpm-platform:wildfly-7.11.0

USER root
ADD camundaSetupScript.sh /camunda/camundaSetupScript.sh
RUN chmod -R 777 /camunda/camundaSetupScript.sh
ADD standalone.xml /camunda/standalone/configuration/standalone.xml
RUN apk add curl
# remove Windows line endings
RUN sed -i -e 's/\r$//' /camunda/camundaSetupScript.sh

RUN /camunda/bin/add-user.sh -p admin -u admin -s

USER camunda
CMD [ "./camundaSetupScript.sh" ]