FROM adoptopenjdk:11-jre-hotspot

# Set the HiveMQ home directory
ENV HIVEMQ_HOME /opt/hivemq

# Copy the extracted HiveMQ files from the builder stage
COPY . ${HIVEMQ_HOME}/

# COPY ./conf/config.xml ${HIVEMQ_HOME}/

RUN ls -al ${HIVEMQ_HOME}/

# Set the working directory to the HiveMQ home directory
WORKDIR ${HIVEMQ_HOME}

# Set the HiveMQ configuration file
COPY conf/config.xml ${HIVEMQ_HOME}/conf/config.xml

# Expose the necessary ports
EXPOSE 1883 8883

# Start HiveMQ
CMD ["bin/run.sh"]