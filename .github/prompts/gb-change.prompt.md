---
description: Start a Global Bank change from a ticket
agent: agent
argument-hint: ticket=GB-nnn goal=...
---
Follow the account-change skill.

Find the GitHub issue in this repository whose title starts with ${input:ticket}. Read that issue,
with its comments and links, using the github MCP tools. Never read the .env file.

Ticket: ${input:ticket}
Goal:   ${input:goal}

Inputs: the files the ticket names, and the file whose style it says to copy.
If you cannot name them, search #codebase, list what you found, and wait for my OK.
