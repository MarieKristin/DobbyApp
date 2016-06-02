#!/bin/bash
curl -i --user $jira_user:$jira_pass \
-H "Content-Type: application/json" \
-H "Accept: application/json" \
-X POST -d '{"fields": {"project":{ "key": "dobby"}, "summary": "CircleCI Build Error", "description": "The build process was not successful. This information was automatically created. Please add further instructions.",  "issuetype": {"name": "Bug" } } }' jira.it.dh-karlsruhe.de:8080/rest/api/2/issue/