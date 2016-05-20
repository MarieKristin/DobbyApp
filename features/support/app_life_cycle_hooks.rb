require 'calabash-android/management/adb'
require 'calabash-android/operations'

Before do |scenario|
  start_test_server_in_background
  wait-for-device
end

After do |scenario|
  if scenario.failed?
    screenshot_embed
  end
  shutdown_test_server
end
