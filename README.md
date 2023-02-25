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

