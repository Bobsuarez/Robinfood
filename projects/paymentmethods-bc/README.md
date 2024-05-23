# Payment Methods Bussines Capability

# NOTAS:

1. El almacenamiento de datos de tarjeta de crédito es cifrado, por tanto se requiere una llave pública y una privada, para generar llaves se puede usar el ejecutable de la aplicación, el comando genera las lineas necesarias para crear las llaves. Estas llaves corresponden a RSA 2048 y permiten cifrar cadenas de texto de hasta 245 caracteres y generan strings de 344 caracteres al ser cifrado.

```bash
user@host$ java -jar paymentmethodsbc*.jar generate-keys
security.encryption.public-key=XXX...
security.encryption.private-key=YYY...
```
