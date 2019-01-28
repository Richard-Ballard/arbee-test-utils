/*
 * (C) Copyright 2016 Richard Ballard.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.richardballard.arbeetestutils.test;

import org.jetbrains.annotations.NotNull;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

/**
 * This class is based on code from http://rolf-engelhard.de/2011/10/fail-instead-of-skip-a-test-when-testngs-dataprovider-throws-an-exception/
 * <p/>
 * By Adding it to your surefire config any tests that throw an exception on test setup will be marked as failures.
 * <p/>
 * It is referenced as a listener from pom.xml - specifically the surefire config, e.g.
 * <blockquote><pre>
 *      &lt;plugin&gt;
 *      &lt;groupId&gt;org.apache.maven.plugins&lt;/groupId&gt;
 *      &lt;artifactId&gt;maven-surefire-plugin&lt;/artifactId&gt;
 *      &lt;version&gt;2.19.1&lt;/version&gt;
 *      &lt;configuration&gt;
 *          &lt;properties&gt;
 *              &lt;!-- Add a listener that treats skipped tests as failures.  If a DataProvider throws then by
 *                   default TestNG will skip the tests that depend on it - we want it to fail. RMB 2016/06/04 --&gt;
 *              &lt;property&gt;
 *                  &lt;name&gt;listener&lt;/name&gt;
 *                  &lt;value&gt;com.github.richardballard.arbeetestutils.test.FailOnSkipTestListenerAdapter&lt;/value&gt;
 *              &lt;/property&gt;
 *          &lt;/properties&gt;
 *      &lt;/configuration&gt;
 *  &lt;/plugin&gt;
 * </pre></blockquote> 
 *
 */
public class FailOnSkipTestListenerAdapter extends TestListenerAdapter {

  @Override
  public void onTestSkipped(@NotNull final ITestResult tr) {

    tr.setStatus(ITestResult.FAILURE);
  }
}