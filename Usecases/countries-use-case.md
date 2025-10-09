## Goal in Context
As a data analyst,
- I want to produce a report of all countries in the world organised by largest to smallest population so that I can support global population analysis.
- I want to produce a report of all countries in a continent organised by largest to smallest population so that I can support continental population reporting.
- I want to produce a report of all countries in a region organised by largest to smallest population so that I can support regional population analysis.
- I want to produce a report of the top N most populated countries in the world so that I can identify the largest nations by population.
- I want to produce a report of the top N most populated countries in a continent so that I can identify key countries by population within each continent.
- I want to produce a report of the top N most populated countries in a region so that I can identify leading countries by population within each region.

## Scope
Organization.

## Level
Primary task.

## Preconditions
The database contains up-to-date country population data for world, continent and region information.

## Success End Condition
A population report is generated and displayed or exported according to the 
selected category (world, continent, region, or top N).

## Failed End Condition
No report is produced.

## Primary Actor
Analyst.

## Trigger 
The organization request for the countries report.

## Main success scenario
- Organization requests a country population report based on a selected category (world, continent, region, or top N).
- System retrieves population data from the world database.
- System organizes and sorts the countries by population in descending order.
- System displays or exports the final population report to the user.

## Extensions
None.

## Sub-variations 
None.

## Schedule
Due Date: Release v0.1.0.1