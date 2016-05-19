require 'calabash-android/management/adb'
require 'calabash-android/operations'

Before do |scenario|
  start_test_server_in_background
  ADB_DEVICE_ARG = emulator-5556
end

After do |scenario|
  if scenario.failed?
    screenshot_embed
  end
  shutdown_test_server
end
