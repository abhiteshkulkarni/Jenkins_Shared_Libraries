def call(String bucketName, String region, String sourceFile, Map options = [:]) {
    // Set defaults if options not provided
    def flatten = options.get('flatten', false)
    def gzipFiles = options.get('gzipFiles', false)
    def useServerSideEncryption = options.get('useServerSideEncryption', true)
    def storageClass = options.get('storageClass', 'STANDARD')
    def profileName = options.get('profileName', 'S3-Creds')
    
    s3Upload(
        consoleLogLevel: 'INFO',
        dontSetBuildResultOnFailure: false,
        dontWaitForConcurrentBuildCompletion: false,
        entries: [[
            bucket: bucketName,
            excludedFile: '',
            flatten: flatten,
            gzipFiles: gzipFiles,
            keepForever: false,
            managedArtifacts: false,
            noUploadOnFailure: false,
            selectedRegion: region,
            showDirectlyInBrowser: false,
            sourceFile: sourceFile,
            storageClass: storageClass,
            uploadFromSlave: false,
            useServerSideEncryption: useServerSideEncryption
        ]],
        pluginFailureResultConstraint: 'FAILURE',
        profileName: profileName,
        userMetadata: []
    )
}
##javaUploadToS3('artifacts-s3-pvt-bkt', 'ap-south-1', '**/*.war')
