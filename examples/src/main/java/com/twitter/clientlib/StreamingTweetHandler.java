/*
Copyright 2020 Twitter, Inc.
SPDX-License-Identifier: Apache-2.0

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
https://openapi-generator.tech
Do not edit the class manually.
*/


package com.twitter.clientlib;


import com.twitter.clientlib.api.TwitterApi;
import com.twitter.clientlib.model.*;

public abstract class StreamingTweetHandler extends StreamingHandler<StreamingTweetResponse> {
  public StreamingTweetHandler(TwitterApi apiInstance) {
    super(apiInstance);
  }

  @Override
  public StreamingTweetResponse getStreamingObject(String tweetString) throws Exception {
    return StreamingTweetResponse.fromJson(tweetString);
  }

  @Override
  public boolean hasReconnectErrors(StreamingTweetResponse streamingTweet) {
    boolean needToReconnect = false;
    if (streamingTweet.getErrors() != null) {
      for (Problem problem : streamingTweet.getErrors()) {
        if (problem instanceof OperationalDisconnectProblem || problem instanceof ConnectionExceptionProblem) {
          System.err.println("Re-connecting to the stream due to: " + problem);
          needToReconnect = true;
          break;
        }
      }
    }
    return needToReconnect;
  }
}

