def ping (var=nil)
   puts "Plic Ploc, dude"
   puts "... and this is your parameter: #{var}"
   return 0
end

def validate_output input_folder='files/example.in', output_folder='files/example.out'
  puts "Started validation"
  input_lines = File.readlines(input_folder)
  rows, cols, min_per_ingredient, max_cells = input_lines[0].split(' ').map(&:to_i)
  pizza = {}
  input_lines[1..-1].map.with_index{ |row, index|
    ingredients = row.delete("\n").split('')
    ingredients.each_with_index{ |ingredient, ingr_index|
      pizza[[index, ingr_index]] = ingredient 
    }
  }
  max = rows * cols
  puts "Read input file: #{rows} rows, #{cols} cols"
  puts "min per ingredient: #{min_per_ingredient}"
  puts "max cells per slice: #{max_cells}"
  puts "max possibile score: #{max}"
  
  output_lines = File.readlines(output_folder)
  slices_number = output_lines[0].to_i
  slices = output_lines[1..-1]
  cells = 0
  puts "Started slices check"
  slices.each_with_index{ |slice, index|
    puts "Check of slice: #{index + 1}"
    r1, c1, r2, c2 = slice.split(' ').map(&:to_i) 
    # check number of slices
    squares_number = (r2 - r1 + 1) * (c2 - c1 + 1)
    raise StandardError.new, "too squares for #{slice}" if squares_number > max_cells
    ingredients = []
    number_of_mushrooms = 0
    number_of_tomatoes = 0
    enough_tomatos = false
    enough_mushrooms = false
    (r1..r2).each{ |r|
      (c1..c2).each{ |c|
        ingredients << pizza[[r,c]]
        pizza[[r,c]] == 'T' ? number_of_mushrooms += 1 : number_of_tomatoes += 1
        enough_tomatos = true if number_of_tomatoes >= min_per_ingredient 
        enough_mushrooms = true if number_of_mushrooms >= min_per_ingredient 
      } 
    }
    raise StandardError.new, "not_enough tomatos and mushs for #{slice} with #{ingredients}" unless enough_tomatos && enough_mushrooms 
    cells += squares_number 
  }
  puts "Score obtained: #{cells} (instead of a maximum of #{max})"
end
