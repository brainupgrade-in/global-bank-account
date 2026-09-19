---
description: Start a Global Bank change from a ticket
agent: agent
argument-hint: ticket=GB-nnn goal=...
---
Follow the account-change skill.

Read course/labs/lab-keys.md to find my Jira key for ${input:ticket}. Read that issue, with its
comments and links, using the atlassian MCP tools. Never read the .env file.

Ticket: ${input:ticket}
Goal:   ${input:goal}

Inputs: the files the ticket names, and the file whose style it says to copy.
If you cannot name them, search #codebase, list what you found, and wait for my OK.
