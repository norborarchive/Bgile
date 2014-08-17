Scenario: Home

Given user access /home page
Then system display page as /home page
And system display title as Bgile
And system display top-link as HOME,SIGNIN,REGISTER
And system display current link as HOME

Given user access /dashboard page
Then system display page as /home page

Given user access /signin page
When user enter nuboat into username
And user enter passwd into password
And user click signin
Then system display page as /signin page

Given user access /signin page
When user enter nuboat into username
And user enter password into password
And user click signin
Then system display page as /dashboard page