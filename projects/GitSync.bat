
@echo off

set /p "dateInitial=Ingresa fecha: "

set "gitPull=git pull"
set "gitCheckout=git checkout master"
set "gitCheckoutEKS=git checkout master-EKS"
set "gitFetch=git fetch --all"
set "gitStash=git stash save "%dateInitial%""

echo stash: , %gitStash%!
echo  ..::: Start changestatus-bc  :::.. !
cd changestatus-bc/ && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start changestatus-or  :::.. !
cd ../changestatus-or && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start configurations-bc  :::.. !
cd ../configurations-bc && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start configurations-pos-bc  :::.. !
cd ../configurations-pos-bc && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start didi-createorder-msg  :::.. !
cd ../didi-createorder-msg && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start didi-responseorder-msg  :::.. !
cd ../didi-responseorder-msg && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start lambdachangeorderstatus-bc  :::.. !
cd ../lambdachangeorderstatus-bc && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start local-printer-bc  :::.. !
cd ../local-printer-bc && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start localorder-bc  :::.. !
cd ../localorder-bc && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start localserver  :::.. !
cd ../localserver && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start localserversocket-ad  :::.. !
cd ../localserversocket-ad && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start notification-manager-msg  :::.. !
cd ../notification-manager-msg && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start notification-manager-or  :::.. !
cd ../notification-manager-or && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start order-bc  :::.. !
cd ../order-bc && %gitStash% && %gitFetch% && %gitCheckoutEKS% && %gitPull%
echo  ************************************ !

echo  ..::: Start order-bc-migrations  :::.. !
cd ../order-bc-migrations && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start order-bc-query  :::.. !
cd ../order-bc-query && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start order-billnumbergenerator-bc  :::.. !
cd ../order-billnumbergenerator-bc && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start order-creation  :::.. !
cd ../order-creation && %gitStash% && %gitFetch% && %gitCheckoutEKS% && %gitPull%
echo  ************************************ !

echo  ..::: Start order-creation-queries  :::.. !
cd ../order-creation-queries && %gitStash% && %gitFetch% && %gitCheckoutEKS% && %gitPull%
echo  ************************************ !

echo  ..::: Start order-or-localserver  :::.. !
cd ../order-or-localserver && %gitStash% && %gitFetch% && %gitCheckoutEKS% && %gitPull%
echo  ************************************ !

echo  ..::: Start order-simbaconnector-bc  :::.. !
cd ../order-simbaconnector-bc && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start orderposresolutions-bc  :::.. !
cd ../orderposresolutions-bc && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start paymentmethods-bc  :::.. !
cd ../paymentmethods-bc && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start posv2  :::.. !
cd ../posv2 && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start queue  :::.. !
cd ../queue && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start routingintegration-bc  :::.. !
cd ../routingintegration-bc && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start routingintegration-or  :::.. !
cd ../routingintegration-or && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start services-changestatus  :::.. !
cd ../services-changestatus && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start services-changestatus-deploy  :::.. !
cd ../services-changestatus-deploy && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start store-or  :::.. !
cd ../store-or && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start taxes-bc  :::.. !
cd ../taxes-bc && %gitStash% && %gitFetch% && %gitCheckoutEKS% && %gitPull%
echo  ************************************ !

echo  ..::: Start test  :::.. !
cd ../test && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start test-maven  :::.. !
cd ../test-maven && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

echo  ..::: Start testQAPrinterCommand  :::.. !
cd ../testQAPrinterCommand && %gitStash% && %gitFetch% && %gitCheckout% && %gitPull%
echo  ************************************ !

cd ..
pause