@abstract 
rule MatchNamedElements // matches Activity, ActivityEdge, ActivityNode, Variable
	match e1 : Left!NamedElement
	with  e2 : Right!NamedElement 
	{
		compare : e1.name = e2.name 
	}
	
rule MatchActivities
	match a1 : Left!Activity
	with a2 : Right!Activity
	extends MatchNamedElements
	{
		compare : true
	}

@greedy
rule MatchActivityNodes
	match n1 : Left!ActivityNode
	with n2 : Right!ActivityNode
	extends MatchNamedElements
	{
		compare : true
	}

@greedy
rule MatchActivityEdges
	match e1 : Left!ActivityEdge
	with e2 : Right!ActivityEdge
	{
		compare: e1.source.matches(e2.source) and e1.target.matches(e2.target)
	}

@greedy
rule MatchVariables
	match v1 : Left!Variable
	with v2 : Right!Variable 
	extends MatchNamedElements
	{
		compare: v1.type() = v2.type()  
	}

rule MatchStringValues
	match v1 : Left!StringValue
	with v2 : Right!StringValue
	{
		compare: v1.value = v2.value
	}
	
rule MatchIntegerValues
	match v1 : Left!IntegerValue
	with v2 : Right!IntegerValue
	{
		compare: v1.value = v2.value
	}
	
rule MatchBooleanValues
	match v1 : Left!BooleanValue
	with v2 : Right!BooleanValue
	{
		compare: v1.value = v2.value
	}
	
@abstract
rule MatchBooleanExpressions
	match e1 : Left!BooleanExpression
	with e2 : Right!BooleanExpression
	{
		compare: e1.assignee.matches(e2.assignee)
	}
	
rule MatchBooleanUnaryExpressions
	match e1 : Left!BooleanUnaryExpression
	with e2 : Right!BooleanUnaryExpression
	extends MatchBooleanExpressions
	{
		compare: e1.operand.matches(e2.operand) and e1.operator = e2.operator
	}

rule MatchBooleanBinaryExpressions
	match e1 : Left!BooleanBinaryExpression
	with e2 : Right!BooleanBinaryExpression
	extends MatchBooleanExpressions
	{
		compare: e1.operand1.matches(e2.operand1) and e1.operand2.matches(e2.operand2) and e1.operator = e2.operator
	}
	
@abstract
rule MatchIntegerExpressions
	match e1 : Left!IntegerExpression
	with e2 : Right!IntegerExpression
	{
		compare: e1.operand1.matches(e2.operand1) and e1.operand2.matches(e2.operand2)
	}
	
rule MatchIntegerCalculationExpressions
	match e1 : Left!IntegerCalculationExpression
	with e2 : Right!IntegerCalculationExpression
	extends MatchIntegerExpressions
	{
		compare: e1.operator = e2.operator and e1.assignee.matches(e2.assignee)
	}

rule MatchIntegerComparisonExpressions
	match e1 : Left!IntegerComparisonExpression
	with e2 : Right!IntegerComparisonExpression
	extends MatchIntegerExpressions
	{
		compare: e1.operator = e2.operator and e1.assignee.matches(e2.assignee)
	}