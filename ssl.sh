#!/bin/bash

# Defina as variáveis
KEYSTORE_PASSWORD="changeit"
TRUSTSTORE_PASSWORD="changeit"
KEY_PASSWORD="changeit"
DNAME="CN=localhost, OU=Test, O=Test, L=Test, S=Test, C=US"
VALIDITY=365
KEYSTORE_FILE="kafka.keystore.jks"
TRUSTSTORE_FILE="kafka.truststore.jks"
CERT_FILE="cert-file"

# Gere o keystore
keytool -genkey -noprompt \
  -alias localhost \
  -dname "$DNAME" \
  -keystore $KEYSTORE_FILE \
  -keyalg RSA \
  -storepass $KEYSTORE_PASSWORD \
  -keypass $KEY_PASSWORD \
  -validity $VALIDITY

# Gere o certificado
keytool -export -alias localhost -file $CERT_FILE -keystore $KEYSTORE_FILE -storepass $KEYSTORE_PASSWORD

# Gere o truststore e importe o certificado
keytool -import -noprompt -alias localhost -file $CERT_FILE -keystore $TRUSTSTORE_FILE -storepass $TRUSTSTORE_PASSWORD

# Limpe o arquivo de certificado temporário
rm $CERT_FILE

echo "Keystore e Truststore gerados com sucesso."