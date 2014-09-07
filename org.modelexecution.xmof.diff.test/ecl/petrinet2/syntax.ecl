rule MatchNet
	match left : Left!Net
	with right : Right!Net
	{
		compare : left.places.matches(right.places) and 
			left.transitions.matches(right.transitions)
	}

rule MatchPlace
	match left : Left!Place
	with right : Right!Place
	{
		compare : left.name = right.name
	}

rule MatchTransition
	match left : Left!Transition
	with right : Right!Transition
	{
		compare : left.name = right.name
	}