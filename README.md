# IP_Project
Semester Project

Instructions to download from GitHub into Eclipse directly:

1. Make sure to have Eclipse Lune (contians Java v8 which has FX implemented directly)

2. Check to see if you have "EGit" plugin preinstalled in eclipse (if not found Help -> Eclipse Marketplace -> search "EGit")

3. Install "e(fx)clipse" plugin (Help -> Eclipse Marketplace -> search "e(fx)clipse") if you don't already have it

4. Go to File -> Import -> Git -> Project from Git (double click)

5. Select "Clone URI" and click Finish

6. Copy and Past URI from Github Project web page (should be on righthand side titled "HTTPS clone URI") and enter github info

7. Press Next (x2) and select Local Destination folder(I recommend a directory seperate from local projects) and press Next (not Finish)

8. Select "Import existing projects" and press Finish


Now before starting a new coding session, you should "pull" the project, in case someone made changes since your last local repository update (last time you "pull"-ed). Now, every time you make a significant change you can "commit" the project (saves changes for git file locally (not a replacement for ctrl+s)). When you are done your coding session, simply "push" the poject which uploads all commits to github servers so that other team members can "pull" what you've just written so that it's also in their local version.

There's a couple of useful "views" you should think about using. Including the "Git Staging" and "Git Repository" views. You can add those by selecting Window -> Views -> Other -> Git -> they are there.
For the "Git Staging" view, it's very simple. Have the project selected in the "Package Explorer" (or "Navigator") view. 
All unstaged (but modified) files will appear in the "Unstaged Changes". Select thefiles to stage for the commit 
(ctrl+a and ctrl+L-Mouse-Click come in handy here) and drag them to the "Staged Changes" area. Write a meaningful
"Commit Message" (ex: structure of MIContainer complete) for the rest of the team and future references 
(when we fuck up we can revert to a specific commit). Now "Commit" or "Commit and Push" and the changes for those
specific files will be saved (locally if only "commit"-ed and  to the server if "Commit and Push"-ed).
Note: "Commit and Push" will also push any previous commits you've made.
