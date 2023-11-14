# Changelog

All notable changes to `cnj-monitoring-quarkus` will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased] - YYYY-MM-DD
### Added
### Changed
### Fixed

## [4.1.0] - 2023-11-14
### Added
- Tagging of git branch
- added missing dependency on assertj
### Changed
- Upgraded to helm-maven-plugin version 5.0.0
- Now a helm chart is packaged and pushed as an artifact during the commit-stage build
- Now the helm chart is pulled before deploying during the integration-test-stage build
- system tests are using the correct JBoss logger now
- removed dependency on cnj-common-test-jakarta by switching to model based system tests
- moved to reactive rest with jackson support to be compliant with other showcases
- upgraded Quarkus version to 3.5.0
- consolidated dependencies

## [4.0.0] - 2023-06-09
### Changed
- moved to new AWS CodeBuild build pipeline
- moved to new CloudTrain EKS cluster
- upgraded everything
- added docker-compose.yml to run the showcase locally

## [3.0.0] - 2023-02-24
### Changed
- upgraded to Java 17
- upgraded Quarkus to 2.16.3
- consolidated with other Quarkus show cases

## [2.2.0] - 2022-08-19
### Changed
- upgraded Quarkus to 2.11.2 
- replaced MicroProfile Metrics with Micrometer as recommended by Quarkus
- activated collection of JDBC DataSource metrics

## [2.1.0] - 2022-08-12
### Changed
- consolidated application-specific prometheus metric names with other showcases

## [2.0.0] - 2022-03-09
### Added
### Changed
- re-release after repo split
