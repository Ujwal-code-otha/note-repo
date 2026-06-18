module.exports = {
  ci: {
    collect: {
      numberOfRuns: 1,
      staticDistDir: './website/out',
      url: [
        '/',
        '/login.html',
        '/register.html',
        '/forgot-password.html',
        '/dashboard.html'
      ]
    },
    assert: {
      assertions: {
        'categories:performance': ['warn', { minScore: 0.9 }],
        'categories:accessibility': ['warn', { minScore: 0.9 }],
        'categories:best-practices': ['warn', { minScore: 0.9 }],
        'categories:seo': ['warn', { minScore: 0.9 }]
      }
    },
    upload: {
      target: 'temporary-public-storage'
    }
  }
};
