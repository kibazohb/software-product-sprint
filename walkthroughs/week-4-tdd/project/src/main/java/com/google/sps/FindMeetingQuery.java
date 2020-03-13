// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/* Problem 
// you'll need to implement a feature that given the meeting information, 
// it will return the times when the meeting could happen that day.
*/

package com.google.sps;

import java.util.Collection;
import java.util.Collections;
import java.util.ArrayList;

public final class FindMeetingQuery {
  private static Collection<TimeRange> result = Collections.emptySet();

  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
 
    if (events == null) {
        result.add(TimeRange.WHOLE_DAY);
    }
    //sort the events by startTime
    ArrayList<Event> eventList = new ArrayList<Event>(events); 

    Collections.sort(eventList, Event.EVENT_START_COMPARATOR);

    return result;
  }
}
