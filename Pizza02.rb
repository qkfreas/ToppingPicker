	require 'yaml'
class Pizza
	def initialize
		topListCreate
		topListLoad
		userAmount
		topRatio
		itemExcluded
		itemIncluded
		amountChange
		saveState
		returnList
	end
	def topListCreate
		@meatTopping = ['Pepperoni','Italian Sausage','Bacon','Chicken','Ham','Philly Steak','Salami','Sliced Italian Sausage']
		@otherTopping = ['Green Peppers','Onions','Diced Tomatoes','Banana Peppers','Black Olives','Jalapeno Peppers','Pinapple','Mushrooms','Spinach','Feta Cheese','Shredded Parmesan']
		@toppingCount = @meatTopping.count + @otherTopping.count
			save01 = @meatTopping.push
				saveState01 = save01.to_yaml
					fileName = 'mt.yml'
						File.open fileName, 'w' do |f|
							f.write saveState01
						end
			save02 = @otherTopping.push
				saveState02 = save02.to_yaml
					fileName = 'ot.yml'
						File.open fileName, 'w' do |f|
							f.write saveState02
						end
	end
	def  topListLoad
		loadAnsr = String.new
			while loadAnsr != ('Yes'||'No')
				print "Would you like to load your previous topping list? (Yes/No) "
					loadAnsr = gets.chomp.capitalize
						if loadAnsr == "Yes"
							@meatTopping = YAML.load_file("smt.yml")
							@otherTopping = YAML.load_file("sot.yml")
							@displayList = (@meatTopping += @otherTopping)
								break
						elsif loadAnsr == "No"
							@meatTopping = YAML.load_file("mt.yml")
							@otherTopping = YAML.load_file("ot.yml")
							@displayList = (@meatTopping + @otherTopping)
								break
						end
			end
	end
	def userAmount
		mech = false
			while mech != true
				puts ""
				print "Enter the amount of desired toppings. (1-#{@displayList.count.to_i}) "
					desired_topping_count = gets.chomp.to_i
					if(desired_topping_count>@toppingCount)
						puts "There are not that many toppings."
					elsif(desired_topping_count<=0)
						puts "Cannot do 0."
					else mech = true
						end
			end
	end
	def topRatio
		@meatNum = Array.new
		@otherNum = Array.new
		mech = false
			while mech != true
				puts "Out of #{@toppingCount} toppings, how many would you like to be meat?"
				print "Enter desired amount or press [Enter] for random. "
					entry = gets.chomp.to_i
						if entry == ""
						@meatNum = rand(@toppingCount)
							@otherNum = (@toppingCount-@meatNum.to_i)
								mech = true
						elsif (entry<=@toppingCount)
							@meatNum = entry
							@otherNum = (@toppingCount-@meatNum.to_i)
								mech = true
						elsif (entry>@toppingCount)
							puts "You have exceeded the amount of desired toppings. "
								mech = false
						end
			end
	end
	def itemExcluded
		meatExclude = Array.new
		otherExclude = Array.new
			entry = true
				while entry == true
					puts ""
					puts "Are there any toppings that you DO NOT want on the list?"
					puts "If not, press [Enter] to continue."
					puts "Here are the toppings that will be included:"
						@meatTopping.sort.each{|e| puts e.inspect}
						@otherTopping.sort.each{|e| puts e.inspect}
					print "Enter undesired topping name here: "
						exclude = gets.chomp.split.map(&:capitalize).join(' ')
							if exclude != ""
									meatExclude.push exclude
								@meatTopping.delete(exclude)
									otherExclude.push exclude
								@otherTopping.delete(exclude)
							else
								entry = false
							end
				end
			@excludeNum = (@meatTopping + @otherTopping)
	end
	def itemIncluded
			entry = true
				while entry != false
					puts ""
					puts "Are there any toppings that you DO want to add to the list?"
					puts "If not, press [Enter] to continue."
					puts "Here are the toppings that will be included: "
						@meatTopping.sort.each{|e| puts e.inspect}
						@otherTopping.sort.each{|e| puts e.inspect}
					print "Enter desired topping name here: "
					include = gets.chomp.capitalize
						if include != ""
							entry = []
								while entry != ("1"||"MEAT"||"M"||"2"||"NON-MEAT"||"NONMEAT"||"NON MEAT"||"NM"||"N")
									puts "Is this topping a meat or non-meat?"
										entry = gets.chomp.upcase
											if entry=("1"||"Meat"||"M")
												@meatTopping.insert(include)
												@meatTopping.push include
											elsif entry = ("2"||"NON-MEAT"||"NONMEAT"||"NON MEAT"||"NM"||"N")
												@otherTopping.insert(include)
												@otherTopping.push include
											end
								end
							else
								entry = false
						end
				end
			@includeNum = (@meatTopping + @otherTopping)
	end
	def amountChange
		if(@includeNum.count.to_i>@excludeNum.count.to_i)
			mech = false
				while mech != true
					puts ""
					puts "The amount of toppings has increased."
					print "Please re-enter your desired amount of toppings. (1-#{@includeNum.count.to_i})"
						@toppingCount = gets.chomp.to_i
					mech = @toppingCount
						if(mech>@includeNum.count.to_i)
							puts "There are not that many toppings."
						elsif(mech<=0)
							puts "Cannot do 0."
						else mech = true
							end
				end
		end
	end
	def saveState
		entry = String.new
			while entry != ("Yes"||"No")
				puts ""
				print "Would you like to save the list? (Yes/No) "
				entry = gets.chomp.capitalize
					if entry == "Yes"
						save = @meatTopping
							saveList = save.to_yaml
						fileName = "mtl.yml"
							File.open fileName, 'w' do |f|
								f.write saveList
							end
						save = @otherTopping
							fileName = "otl.yml"
								File.open fileName, 'w' do |f|
									f.write saveList
								end
						puts "Your options have been saved!"
						puts ""
							break
					elsif entry == "No"
						puts "Your list has not been saved."
						puts ""
							break
					end
			end
	end
	def returnList
		puts @meatNum
			subtractedM = String.new
		if(@toppingCount>1)
			reply = "s are"
		else
			reply = " is"
		end
			puts "Your #{@toppingCount} topping#{reply}: "
		@meatNum.to_i.times do
		#puts "MECH== #{@meatTopping} ////// #{@otherTopping}"
			mechM = @meatTopping.count.to_i
				randomM = (rand(mechM-subtractedM.to_i-@excludeNum+@includeNum))
					@meatTopping.delete_at(randomM.to_i)
				remainingM = @meatTopping.count.to_i
				subtractedM=(mechM.to_i-randomM.to_i)
					puts @meatTopping
			end
	end	
end
Pizza.new