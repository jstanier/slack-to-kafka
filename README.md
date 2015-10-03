# slack-to-kafka
## What does this do?

It's a standalone application that will push all of the messages from the channel that your [Slack bot](https://api.slack.com/bot-users) is in to [Apache Kafka](http://kafka.apache.org).

## Pre-requisites

### Setting up a Slack bot
If you don't have a bot set up in your channel then you'll need to do that first. There are [good instructions](https://api.slack.com/bot-users) available to show you how to do this. Then you'll need to invite the bot into channels that you're interested in receiving messages from.

When you set up the Slack bot, you'll be given an API token. This is the token that you'll need to provide to this program for the Slack connection to work. Note it down somewhere!

### application.yml
Copy the [`application_sample.yml`](https://github.com/jstanier/slack-to-kafka/blob/master/config/application_sample.yml) file to a new file called `application.yml` in the same folder. That's where you'll put your Slack bot's API token.

### Running Kafka
You may already have Kafka running on your machine or on a remote machine. If so, then good for you! Provide the list of VIP brokers to the `application.yml` file along with the topic that you'll want messages pushed to.

## Building and running the application
TBD
