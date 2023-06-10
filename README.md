# cnj-monitoring-backend-quarkus

Cloud native Quarkus backend with support of cluster monitoring based on Micrometer.

> Note: Quarkus is now favouring `Micrometer` over `MicroProfile Metrics`.
> Although MicroProfile Metrics is still supported, Quarkus recommends to switch to Micrometer.

> Note: If you want to activate collection of JDBC DataSource metrics you need to set 
> appliction property `quarkus.datasource.metrics.enabled` to true!

## Status

![Build status](https://codebuild.eu-west-1.amazonaws.com/badges?uuid=eyJlbmNyeXB0ZWREYXRhIjoiZS9rOXN0UXNRcUNoSnlNU0VpaVZqM3RIWTVnanlIODdmRkZNcHZxS056S1YrbEh2Y013Q3BqUzRLZ3Rjbk8yb1dWTC9Ea1dBRm5JNTZtajByT2E4WmVzPSIsIml2UGFyYW1ldGVyU3BlYyI6IlVRVm43Z0hHY2hpTlI4aTgiLCJtYXRlcmlhbFNldFNlcmlhbCI6MX0%3D&branch=main)

## Release information

Check [changelog](changelog.md) for latest version and release information.

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

