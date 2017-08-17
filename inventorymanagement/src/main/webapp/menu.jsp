		     <nav id="nav-bar">
		        <ul>
		          <li id="request">                     <a href="request.jsp">              request an item     </a>    </li>
		        <sec:authorize access="hasRole('SUPERVISOR')">
		          <li id="approvals">                    <a href="review-approvals.jsp">     review approvals    </a>    </li>
		        </sec:authorize>
		        <sec:authorize access="hasRole('ADMINISTRATOR')">
		          <li id="manage-items">  <a href="listAllInventories.jsp">   manage items        </a>    </li>
		          <li id="manage-users">  <a href="listAllUsers.jsp">         manage users        </a>    </li>
		          <li id="reports">  <a href="listAllRequests.jsp">           requests report        </a>    </li>
		        </sec:authorize>
		        </ul>
		      </nav>
            <script type="text/javascript">
            function setCurrentLink(windowPathname){
            	console.log("my current windowHostname is: " + windowPathname);
	            switch(true) {
            	case  /listAllInventories/.test(windowPathname) :
	            	console.log("we have a match on approvals:  " + windowPathname);
	            	$("#manage-items").addClass("current-link");
	            	break;
	            case  /request/.test(windowPathname) :
	            case  /request\/complete/.test(windowPathname) :
	            case  /inventorymanagement\/request\/begin/.test(windowPathname) :
	            	console.log("we have a match on request:  " + windowPathname);
	            	$("#request").addClass("current-link");
	            	break;
	            case  /approvals/.test(windowPathname) :
	            case  /supervisorApproved/.test(windowPathname) :
	            	console.log("we have a match on request:  " + windowPathname);
	            	$("#approvals").addClass("current-link");
	            	break;
	            case  /item/.test(windowPathname) :
	            	console.log("we have a match on item:  " + windowPathname);
	            	$("#manage-items").addClass("current-link");
	            	break;
	            case  /listAllUsers/.test(windowPathname) :
	            	console.log("we have a match on item:  " + windowPathname);
	            	$("#manage-users").addClass("current-link");
	            	break;
	            case  /listAllRequests/.test(windowPathname) :
	            	console.log("we have a match on item:  " + windowPathname);
	            	$("#reports").addClass("current-link");
	            	break;
	            }
            }
            this.setCurrentLink(window.location.pathname);
            
            </script>