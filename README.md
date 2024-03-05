# cnj-monitoring-backend-quarkus

Cloud native Quarkus backend with support of cluster monitoring based on Micrometer.

The application is packaged as a multi-architecture docker image which supports the following platforms:
* linux/amd64
* linux/arm64/v8

## Synopsis

This showcase demonstrates
* how to enable exposure of monitoring data in Prometheus format
* how to enable scraping of monitoring data by Prometheus
* how to publish custom monitoring data with annotations

> Note: Quarkus is now favouring `Micrometer` over `MicroProfile Metrics`.
> Although MicroProfile Metrics is still supported, Quarkus recommends to switch to Micrometer.

> Note: If you want to activate collection of JDBC DataSource metrics you need to set
> appliction property `quarkus.datasource.metrics.enabled` to true!

### Enable exposure of monitoring data in Prometheus format

Actually, exposure of monitoring data in Prometheus format is supported by any MicroProfile compliant
application server by default.

The Prometheus data are published via path `/q/metrics` via the HTTP endpoint at port `8080`.
If your application listens on localhost at port 8080 for HTTP requests,
the Prometheus data can be retrieved via URL: `http://localhost:8080/q/metrics`.

### Enable scraping of monitoring data by Prometheus

Prometheus running on Kubernetes is capable of detecting applications exposing monitoring data
automatically, as long as they are marked correctly.
Depending on the way Prometheus is installed on the Kubernetes cluster,
your application needs to be are marked in two different ways:

* Prometheus Operator Installation (default): you must add a ServiceMonitor resource to your Helm chart
* Prometheus Basic Installation (legacy): you must add certain annotations to your Pod

#### Adding a ServiceMonitor to your Helm Chart

When using the Prometheus Operator Installation you need to add an additional resource - a so-called `ServiceMonitor` - to your deployment.

A concrete sample for a ServiceMonitor Helm template can be found in [src/main/helm/cnj-monitoring-backend-micro/templates/servicemonitor.yaml](src/main/helm/cnj-monitoring-backend-micro/templates/servicemonitor.yaml).
A concrete Kubernetes manifest for a ServiceMonitor will look like this:

```yaml
apiVersion: monitoring.coreos.com/v1
kind: ServiceMonitor
metadata:
  name: cnj-monitoring-backend-quarkus
  labels:
    helm.sh/chart: cnj-monitoring-backend-quarkus-5.0.0
    app.kubernetes.io/name: cnj-monitoring-backend-quarkus
    app.kubernetes.io/instance: cnj-monitor-backend-quarkus
    app.kubernetes.io/version: "5.0.0.LOCAL.12345678"
    app.kubernetes.io/managed-by: Helm
spec:
  endpoints:
    - port: http
      path: /q/metrics
  selector:
    matchLabels:
      app.kubernetes.io/name: cnj-monitoring-backend-quarkus
      app.kubernetes.io/instance: cnj-monitor-backend-quarkus
  namespaceSelector:
    matchNames:
      - cloudtrain
```
The `spec.endpoints` element specifies port and path of the metrics endpoint.
The `spec.selector` element attaches the `ServiceMonitor` to all pods of the application.

The Prometheus service will detect new ServiceMonitors and start to scrape the monitoring data automatically.

#### Annotating your Pod

When using a basic installation of Prometheus, you will need to annotate your Pod with Prometheus annotations:

| Annotation            | Example           | Description                  |
|-----------------------|-------------------|------------------------------|
| `prometheus.io/scrape` | "true" or "false" | Controls if Prometheus should consider this Pod for scraping. |     
| `prometheus.io/path`  | "/q/metrics"      | Path of the metrics endpoint. |
| `prometheus.io/port`   | "8080"            | Port number of the metrics endpoint. | 

The Prometheus service will detect new annotated Pods with `prometheus.io/scrape` set to `true` and start to scrape the monitoring data automatically.

## Status

![Build status](https://codebuild.eu-west-1.amazonaws.com/badges?uuid=eyJlbmNyeXB0ZWREYXRhIjoiZS9rOXN0UXNRcUNoSnlNU0VpaVZqM3RIWTVnanlIODdmRkZNcHZxS056S1YrbEh2Y013Q3BqUzRLZ3Rjbk8yb1dWTC9Ea1dBRm5JNTZtajByT2E4WmVzPSIsIml2UGFyYW1ldGVyU3BlYyI6IlVRVm43Z0hHY2hpTlI4aTgiLCJtYXRlcmlhbFNldFNlcmlhbCI6MX0%3D&branch=main)

## Release information

Check [changelog](changelog.md) for latest version and release information.

## Docker Pull Command

`docker pull docker.cloudtrain.aws.msgoat.eu/cloudtrain/cnj-monitoring-backend-quarkus`

## Helm Pull Command

`helm pull oci://docker.cloudtrain.aws.msgoat.eu/cloudtrain-charts/cnj-monitoring-backend-quarkus`

## HOW-TO build this application locally

If all prerequisites are met, just run the following Maven command in the project folder:

```shell 
mvn clean verify -P pre-commit-stage
```

Build results: a Docker image containing the showcase application.

## HOW-TO run this showcase locally

In order to run the whole showcase locally, just run the following docker commands in the project folder:

```shell 
docker compose up -d
docker compose logs -f 
```
The showcase application will be accessible via `http://localhost:38090`.

The prometheus telemetry data will be available at `http://localhost:38090/q/metrics`.

Press `Ctlr+c` to stop tailing the container logs and run the following docker command to stop the show case:

```shell 
docker compose down
```

