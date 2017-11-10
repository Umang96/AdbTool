echo "Startig script for pulling $1"
rm -rf $1
mkdir $1
echo "Finding $1 files now..."
adb shell ls -R /sdcard/ |
    while read line
    do
            line=$(echo $line | tr -d '\r')

            if [[ "$line" =~ ^/.*:$ ]]
            then
                    dir=${line%:}

            elif ([ "$line" = "opendir failed, Permission denied" ])
            then
                    echo "find: $dir: Permission denied"

            else
                if ([ "$dir" = "/" ]); then dir=""; fi
		
		newline="$dir/$line"
		if [[ $newline == *.$1* ]]; then
  		echo $newline
		adb pull "$newline" $1/
		fi
       
            fi
    done
echo "Done pulling files"

