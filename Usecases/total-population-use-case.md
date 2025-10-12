## Goal in Context
As a data analyst,
- I want to produce a report of the total population, people living in cities, and people not living in cities in each continent so that I can analyse urbanisation by continent.
- I want to produce a report of the total population, people living in cities, and people not living in cities in each region so that I can analyse regional urbanisation trends.
- I want to produce a report of the total population, people living in cities, and people not living in cities in each country so that I can understand urbanisation at the national level.
- I want to view the total population of the world so that I can provide global demographic insights.
- I want to view the total population of a continent so that I can support demographic reporting by continent.
- I want to view the total population of a region so that I can support demographic analysis by region.
- I want to view the total population of a country so that I can report on national demographics.
- I want to view the total population of a district so that I can evaluate local population trends.
- I want to view the total population of a city so that I can provide accurate city-level demographic information.

## Scope
Organization.

## Level
Primary task.

## Preconditions
The database contains up-to-date and view total population data, people living and not living in the cities
for world, continent, region, country, city and district information.

## Success End Condition
A population report is generated, displayed or exported, city population, and non-city population according to the
selected category (world, continent, region, country, district and city).

## Failed End Condition
No report is produced.

## Primary Actor
Analyst.

## Trigger
The organization request for the population report.

## Main success scenario
- Organization requests a population report based on a selected category (world, continent, region, country, district and city).
- System retrieves population data from the world database.
- The system calculates urbanisation percentages and aggregates data as needed.
- The system generates a report with total, city, and non-city populations.
- System displays or exports the final population report to the user.

## Extensions
None.

## Sub-variations
None.

## Schedule
Due Date: Release v0.1.0.3