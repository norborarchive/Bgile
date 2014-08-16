Scenario: Home

Given user access /home page
Then system display page as /home page
And system display title as Bgile

Given user access /dashboard page
When wait for 1
Then system display page as /home page

Given user access /signin page
When user enter nuboat into username
And user enter password into passwd
And user click signin
And wait for 1
Then system display page as /signin page

Given user access /signin page
When user enter nuboat into username
And user enter password into password
And user click signin
And wait for 1
Then system display page as /dashboard page