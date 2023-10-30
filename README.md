# matomoResourceGenerator
Custom resource generator for Matomo

The [Glasskube Kubernetes Operator](https://github.com/glasskube/operator) installs and automatically updates open
source tools in a customers Kubernetes cluster. As every Kubernetes operator it watches for specific Kubernetes
resources which follow a custom resource definition and creates, updates and monitors other resources based on them.

## Matomo demand increases

Glasskube experiences an increase in demand for managed Matomo instances.
Hundreds of instances must be installed every day.
You, as a developer, would have to spend hours creating these resource files per hand.
But you come up with a different alternative: Why don't we create a custom resource generator for Matomo?

Together with team you came up with following specifications. It's now your task create the Matomo Generator.

## Specifications

### REST API

Create a Kotlin Spring Boot Application that exposes a JSON Rest API for creating Matomo resources to be used
by the Glasskube Operator.

Such a resource, as handled by the operator - the desired output, might look like this:

```yaml
apiVersion: "glasskube.eu/v1alpha1"
kind: Matomo
metadata:
  name: matomo
  namespace: matomo
spec:
  host: matomo.minikube
```

The properties `metadata.name`, `metadata.namespace` and `spec.host` should be derived from the request body.

This yaml file should also be returned via the REST API.

In addition, there should be an endpoint that returns all the generated resources.

### Persistence

Use a SQL database for persistence.

### Service validation

Creating two resources with the same name in the same namespace would be an error.
Therefore, your application should validate that it doesn't generate two resources with the same name in the same
namespace.

NOTE:
- create table before running the app

