deploy-secrets:
	minikube kubectl -- apply -f backend-secrets.yaml

deploy-deployment:
	minikube kubectl -- apply -f deployment.yaml

deploy-service:
	minikube kubectl -- apply -f service.yaml

deploy:
	make deploy-secrets
	make deploy-deployment
	make deploy-service
