FROM camunda/camunda-bpm-platform:wildfly-7.11.0

USER root
ADD camundaSetupScript.sh /camunda/camundaSetupScript.sh
ADD standalone.xml /camunda/standalone/configuration/standalone.xml
RUN apk add curl
# remove Windows line endings
RUN sed -i -e 's/\r$//' /camunda/camundaSetupScript.sh

USER camunda
CMD [ "./camundaSetupScript.sh" ]