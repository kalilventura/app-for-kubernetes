deploy-config:
	kubectl apply -f postgres-configmap.yaml

deploy-secrets:
	kubectl apply -f postgres-secrets.yaml

deploy-pv:
	kubectl apply -f postgres-pv.yaml

deploy-pvc:
	kubectl apply -f postgres-pvc.yaml

deploy-deployment:
	kubectl apply -f deployment.yaml

deploy-service:
	kubectl apply -f service.yaml

deploy:
	make deploy-config
	make deploy-secrets
	make deploy-pv
	make deploy-pvc
	make deploy-deployment
	make deploy-service
