def call(String bucketName, String region, String buildDir = 'build', Map options = [:]) {
    // Install dependencies and build React app
    sh 'npm install'
    sh 'npm run build'

    // Upload all files inside the build directory to S3
    s3Upload(
        consoleLogLevel: 'INFO',
        dontSetBuildResultOnFailure: false,
        dontWaitForConcurrentBuildCompletion: false,
        entries: [[
            bucket: bucketName,
            excludedFile: '',
            flatten: false,
            gzipFiles: false,
            keepForever: false,
            managedArtifacts: false,
            noUploadOnFailure: false,
            selectedRegion: region,
            showDirectlyInBrowser: false,
            sourceFile: "${buildDir}/**",
            storageClass: options.get('storageClass', 'STANDARD'),
            uploadFromSlave: false,
            useServerSideEncryption: options.get('useServerSideEncryption', false)
        ]],
        pluginFailureResultConstraint: 'FAILURE',
        profileName: options.get('profileName', 'S3-Creds'),
        userMetadata: []
    )
}
##deployReactAppToS3('my-react-bucket', 'us-east-1')
