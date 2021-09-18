# Events API
## Installation and Setup

#### Clone Repository

1. Right click in a directory and open Git Bash
2. Type in console ```git clone https://github.com/ityrpak/Events-API.git```

#### Environment Variables

| Name                   | Description                          | Example                 |
| ---------------------- | ------------------------------------ | ----------------------- |
| H2_PORT                | H2 jdbc and database port            | jdbc:h2:mem             |
| H2_DATABASENAME        | Set database name                    | testdb                  |
| H2_USERNAME            | H2 username                          | sa                      |
| H2_PASSWORD            | H2 password                          | sa                      |
| JWT_SECRET             | JWT secret word for token generation | AVeryVeryVeryLongString |
| JWT_EXPIRATION_TIME_MS | JWT expiration time                  | 900000                  |

## Overview

Events API is a project consisting of a RESTful API for Events organization. Users are able to register into the system and create events, where users are able to confirm assistance. Users are able to get a summary of all subscribed and created events.

## Roadmap (WIP)

- [ ] V 0.1
  - Users are able to register and authenticate.
  - Users are able to create events while logged in.
  - Anonymous are able to get a complete event list.
- [ ] V 0.1.5
  - Users are able to update their personal data.
  - Users are able to modify, delete their own created events.
- [ ] V 0.2
  - Users are able to subscribe to events.
  - Users are able to get a report of all subscribed and created events.
- [ ] V 0.3
  - Add mail service.
  - Add mail confirmation upon registration.
  - Add mail reminders for subscribed events.
- [ ] V 0.4
  - Add image service.
  - Users are able to upload a profile photo.
  - Users are able to upload an event photo.
- [ ] V 0.5
  - Add events categories.
  - Add public and private events category. Users must have a code issued by the event author in order to subscribe.
  - Anonymous must not be able to list private events.
- [ ] V 0.6
  - Add events commentaries.
  - Users must be able to modify, delete and answer to comments.
  - Add moderator role type. It must be able to modify and/or delete comments and public events.
- [ ] V 0.7
  - Add user friend feature.
  - Add private message feature.
  - Users are able to create events only visible to friends.
- [ ] V 0.8
  - Add company user type.
  - Users are able to subscribe to companies to get the latest news.
  - Companies are able to get status report about all events and subscribed users.
- [ ] V 0.9
  - Google Maps integration for event localization.
- [ ] V 0.9.5
  - Add Zoom API integration.
- [ ] V 1.0 (RELEASE)
  - MercadoPago API Integration.
- [ ] V 1.1 ... and beyond!

## Key Topics

## Useful Links
