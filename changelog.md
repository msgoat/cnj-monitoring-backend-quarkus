# Changelog

All notable changes to `cnj-monitoring-quarkus` will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased]

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
