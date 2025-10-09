## Goal in Context
As a data analyst, 
- I want to produce a report of all capital cities in the world organised by largest to smallest population so that I can support analysis of global capitals.
- I want to produce a report of all capital cities in a continent organised by largest to smallest population so that I can analyse continental capitals by population.
- I want to produce a report of all capital cities in a region organised by largest to smallest population so that I can analyse regional capital population trends.
- I want to produce a report of the top N most populated capital cities in the world so that I can identify the world’s largest capitals.
- I want to produce a report of the top N most populated capital cities in a continent so that I can identify leading continental capitals.
- I want to produce a report of the top N most populated capital cities in a region so that I can identify major capitals within each region.

## Scope
Organization.

## Level
Primary task.

## Preconditions
The database contains up-to-date capital city population data for world, continent and region information.

## Success End Condition
A population report is generated and displayed or exported according to the
selected category (world, continent, region, or top N).

## Failed End Condition
No report is produced.

## Primary Actor
Analyst.

## Trigger
The organization request for the capital cities report.

## Main success scenario
- Organization requests a capital city population report based on a selected category (world, continent, region, or top N).
- System retrieves population data from the world database.
- System organizes and sorts the capital cities by population in descending order.
- System displays or exports the final population report to the user.

## Extensions
None.

## Sub-variations
None.

## Schedule
Due Date: Release v0.1.0.2