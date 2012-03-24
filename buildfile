require 'buildr/cobertura'
require 'buildr/jdepend'
require 'buildr-findBugs'

repositories.remote << 'http://download.java.net/maven/2/'
repositories.remote << 'http://repo1.maven.org/maven2/'

VERSION_NUMBER = '0.5-SNAPSHOT'

GOOGLE_DATA = struct(
  :core => 'com.google.gdata:gdata-core:jar:1.0',
  :media => 'com.google.gdata:gdata-media:jar:1.0',
  :photo => 'com.google.gdata:gdata-photos:jar:2.0'
)

COMMONS = struct(
  :codec => 'commons-codec:commons-codec:jar:1.4',
  :lang => 'commons-lang:commons-lang:jar:2.6',
  :logging => 'commons-logging:commons-logging:jar:1.1.1'
)

LOG = struct(
  :slf => group('slf4j-api', 'slf4j-log4j12', :under => 'org.slf4j', :version => '1.6.4'),
  :log4j => 'log4j:log4j:jar:1.2.16'
)

JETTY = group('jetty-continuation', 'jetty-http', 'jetty-io', 'jetty-server', 'jetty-util', :under => 'org.eclipse.jetty', :version => '8.1.2.v20120308')

desc 'pwad'
define 'pwad' do
  project.group = 'org.desgrange'
  project.version = VERSION_NUMBER

  compile.using :source => '1.6',
                :target => '1.6',
                :other=>['-encoding', 'utf-8']
  compile.with GOOGLE_DATA.core, GOOGLE_DATA.media, GOOGLE_DATA.photo, LOG.slf, LOG.log4j, COMMONS.codec, COMMONS.lang, COMMONS.logging,
    'com.apple:AppleJavaExtensions:jar:1.4', 
    'com.google.collections:google-collections:jar:1.0', 
    'javax.activation:activation:jar:1.1.1', 
    'javax.mail:mail:jar:1.4.4', 
    'org.apache.httpcomponents:httpclient:jar:4.1.2', 
    'org.apache.httpcomponents:httpcore:jar:4.1.2'

  run.using :main => "net.desgrange.pwad.Main"

  test.with JETTY, 
    'commons-io:commons-io:jar:2.1', 
    'junit:junit-dep:jar:4.10',
    'org.hamcrest:hamcrest-core:jar:1.1', 
    'org.mockito:mockito-core:jar:1.9.0', 
    'org.mortbay.jetty:servlet-api:jar:3.0.20100224', 
    'org.objenesis:objenesis:jar:1.0', 
    'org.uispec4j:uispec4j:jar:jdk16:2.4'

  package :jar, :id => 'pwad'
  
  task :pmd do
    pmd_classpath = transitive('pmd:pmd:jar:4.3').each(&:invoke).map(&:to_s).join(File::PATH_SEPARATOR)
    ant("pmd-report") do |ant|
      ant.taskdef :name=> 'pmd', :classpath=>pmd_classpath, :classname=>'net.sourceforge.pmd.ant.PMDTask'
      ant.pmd :rulesetfiles => 'basic,imports,unusedcode' do
        ant.formatter :type=>'xml', :toFile=> _(:reports, 'pmd.xml')
        compile.sources.each do |src|
          ant.fileset :dir=> src, :includes=>'**/*.java'
        end
      end
    end
  end
  
  task :reports => ['cobertura:xml', 'jdepend:xml', :pmd, 'findBugs']
end
