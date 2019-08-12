#!/usr/bin/env bash

# Install kubectl.
curl -Lo kubectl https://storage.googleapis.com/kubernetes-release/release/v1.10.0/bin/linux/amd64/kubectl
chmod +x kubectl && sudo mv kubectl /usr/local/bin/kubectl

# Install Skaffold.
curl -Lo skaffold https://storage.googleapis.com/skaffold/releases/v0.15.0/skaffold-linux-amd64
chmod +x skaffold && sudo mv skaffold /usr/local/bin/skaffold

# Install Helm.
HELM_URL=https://storage.googleapis.com/kubernetes-helm
HELM_TGZ=helm-v2.9.0-linux-amd64.tar.gz
wget -q ${HELM_URL}/${HELM_TGZ}
tar xzfv ${HELM_TGZ}
PATH=`pwd`/linux-amd64/:$PATH

# Log into IBM Container Registry.
echo "$IBM_CR_TOKEN" | docker login --username token --password-stdin registry.ng.bluemix.net/gtd1

# Setup Kubectl.
export KUBECONFIG=$(pwd)/kubeconfig.yaml
kubectl config set-cluster cluster --server "$CLUSTER_SERVER"
kubectl config set clusters.cluster.certificate-authority-data "$CLUSTER_CA_CERT"
kubectl config set-credentials deploytools --token "$CLUSTER_TOKEN"
kubectl config set-context context --cluster cluster --namespace "$CLUSTER_NAMESPACE" --user deploytools
kubectl config use-context context
