## Goal in Context
As a data analyst, 
- I want to produce a report of all cities in the world organised by largest to smallest population so that I can analyse global urban population distribution.
- I want to produce a report of all cities in a continent organised by largest to smallest population so that I can analyse urban trends by continent.
- I want to produce a report of all cities in a region organised by largest to smallest population so that I can analyse regional urban growth.
- I want to produce a report of all cities in a country organised by largest to smallest population so that I can understand population distribution within that country.
- I want to produce a report of all cities in a district organised by largest to smallest population so that I can evaluate population spread within districts.
- I want to produce a report of the top N most populated cities in the world so that I can identify the world’s major urban centres.
- I want to produce a report of the top N most populated cities in a continent so that I can identify the largest cities in each continent.
- I want to produce a report of the top N most populated cities in a region so that I can identify the largest cities within that region.
- I want to produce a report of the top N most populated cities in a country so that I can identify major cities by population within that country.
- I want to produce a report of the top N most populated cities in a district so that I can highlight the largest urban areas within each district.

## Scope
Organization.

## Level
Primary task.

## Preconditions
The database contains up-to-date city population data for world, continent, region, country and district information.

## Success End Condition
A population report is generated and displayed or exported according to the
selected category (world, continent, region, country, district or top N).

## Failed End Condition
No report is produced.

## Primary Actor
Analyst.

## Trigger
The organization request for the cities report.

## Main success scenario
- Organization requests a city population report based on a selected category (world, continent, region, country, district or top N).
- System retrieves population data from the world database.
- System organizes and sorts the cities by population in descending order.
- System displays or exports the final population report to the user.

## Extensions
None.

## Sub-variations
None.

## Schedule
Due Date: Release v0.1.0.2