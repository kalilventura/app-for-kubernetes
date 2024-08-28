deploy-config:
	minikube kubectl -- apply -f postgres-configmap.yaml

deploy-secrets:
	minikube kubectl -- apply -f postgres-secrets.yaml

deploy-pv:
	minikube kubectl -- apply -f postgres-pv.yaml

deploy-pvc:
	minikube kubectl -- apply -f postgres-pvc.yaml

deploy-deployment:
	minikube kubectl -- apply -f deployment.yaml

deploy-service:
	minikube kubectl -- apply -f service.yaml

deploy:
	make deploy-config
	make deploy-secrets
	make deploy-pv
	make deploy-pvc
	make deploy-deployment
	make deploy-service
