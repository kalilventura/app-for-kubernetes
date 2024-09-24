deploy-config:
	kubectl apply -f backend-configmap.yaml

deploy-secrets:
	kubectl apply -f backend-secrets.yaml

deploy-deployment:
	kubectl apply -f deployment.yaml

deploy-service:
	kubectl apply -f service.yaml

deploy:
	make deploy-config
	make deploy-secrets
	make deploy-deployment
	make deploy-service
