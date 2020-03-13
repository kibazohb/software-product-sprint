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
  private static Collection<TimeRange> result = new ArrayList<TimeRange>();

  public Collection<TimeRange> query(Collection<Event> events, MeetingRequest request) {
 
    if (request.getAttendees().size() == 0) {
        result.add(TimeRange.WHOLE_DAY);
        return result;
    }

    //sort the events by startTime
    ArrayList<Event> eventList = new ArrayList<Event>(events); 
    Collections.sort(eventList, Event.EVENT_START_COMPARATOR);

    TimeRange TIME_ENDPOINTS = TimeRange.fromStartDuration(TimeRange.END_OF_DAY, TimeRange.START_OF_DAY);
    int duration = (int) request.getDuration();
    TimeRange firstMeetingTime = eventList.get(0).getWhen();
    TimeRange lastMeetingTime = eventList.get(eventList.size() - 1).getWhen();

    if (meetingTimeWillFit(TIME_ENDPOINTS,firstMeetingTime, duration)) {
        result.add(TimeRange.fromStartEnd(TIME_ENDPOINTS.end(), firstMeetingTime.start(), false));
    }
    System.out.println(result);
    int curr_ptr = 0;
    int next_ptr = curr_ptr + 1;

    while (next_ptr < eventList.size()) {
        TimeRange currMeetingTime = eventList.get(curr_ptr).getWhen();
        TimeRange nextMeetingTime = eventList.get(next_ptr).getWhen();
        
        if (currMeetingTime.contains(nextMeetingTime)) {
            next_ptr += 1;
            continue;
        }

        if (!nextMeetingTime.overlaps(currMeetingTime)) {
          
          if (meetingTimeWillFit(currMeetingTime, nextMeetingTime, duration)) {
              result.add(TimeRange.fromStartEnd(currMeetingTime.end(), nextMeetingTime.start(), false));     
          }
        }
        curr_ptr = next_ptr;
        next_ptr += 1;
    }

    if (meetingTimeWillFit(lastMeetingTime, TIME_ENDPOINTS, duration)) {
        result.add(TimeRange.fromStartEnd(lastMeetingTime.end(), TIME_ENDPOINTS.start(), true));
    }
    System.out.println(result);
    System.out.println("hi");
    return result;
  }
   
  public static boolean meetingTimeWillFit(TimeRange currMeetingTime, TimeRange nextMeetingTime, int duration) {
      return (nextMeetingTime.start() - currMeetingTime.end() >= duration);
  }
}
