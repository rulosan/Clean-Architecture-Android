/*
 * Copyright (C) 2016 Erik Jhordan Rey.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.jhordan.euro_cleanarchitecture.data.repository;

import com.example.jhordan.euro_cleanarchitecture.data.entity.TeamEntity;
import com.example.jhordan.euro_cleanarchitecture.data.local.ApiInterface;
import com.example.jhordan.euro_cleanarchitecture.data.repository.datasource.TeamsApiDataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class) public class TeamApiInterfaceDataSourceTest {

  private static final String ANY_FLAG_OF_TEAM_ENTITY = "ESP";

  @Mock
  ApiInterface apiInterface;

  private TeamsApiDataSource localApiDataSource;

  @Before public void setUp() {
    localApiDataSource = new TeamsApiDataSource(apiInterface);
  }

  @Test public void givenATeamEntityListFromLocalApi() {
    localApiDataSource.teamEntityList();
    verify(apiInterface).teamEntityList();
  }

  @Test public void givenATeamEntityByFlagFromLocalApi() {
    localApiDataSource.teamEntity(ANY_FLAG_OF_TEAM_ENTITY);
    verify(apiInterface).teamEntity(ANY_FLAG_OF_TEAM_ENTITY);
  }

  @Test public void givenAnObservableCollectionTeamEntity() {
    List<TeamEntity> teamEntities = new ArrayList<>();
    Observable<List<TeamEntity>> fakeListObservable = Observable.just(teamEntities);
    given(apiInterface.teamEntityList()).willReturn(fakeListObservable);
  }

  @Test public void givenAnObservableTeamEntity() {
    TeamEntity fakeEntity = new TeamEntity();
    Observable<TeamEntity> fakeObservable = Observable.just(fakeEntity);
    given(apiInterface.teamEntity(ANY_FLAG_OF_TEAM_ENTITY)).willReturn(fakeObservable);
  }
}
