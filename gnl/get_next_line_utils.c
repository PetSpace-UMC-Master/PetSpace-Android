#include "get_next_line.h"

char	*ft_substr(char const *s, unsigned int start, size_t len)
{
	size_t	i;
	size_t	j;
	char	*cpy;

	i = 0;
	j = 0;
	if (!s)
		return (NULL);
	cpy = malloc(len + 1);
	if (!cpy)
		return (NULL);
	while (s[i++])
	{
		if (i >= start && j < len)
			cpy[j++] = s[i];
	}
	cpy[j] = '\0';
	return (cpy);
}

int	ft_strlen(const char *str)
{
	int	i;

	i = 0;
	while (str[i])
		i++;
	return (i);
}

char	*ft_strchr(const char *str, int c)
{
	char	*p;
	p = (char *)str;
	while (*p)
	{
		if (*p == c)
			return (p);
		else
			p++;
	}
	if (*p == c)
		return (p);
	return (0);
}

char *ft_strdup(const char *s1)
{
	int	len;
	char	*p;
	int	i;

	len = 0;
	i = 0;
	while (s1[i++])
		len ++;
	p = malloc(sizeof(char) * (len + 1));
	if (!p)
		return (NULL);
	i = 0;
	while (i < len)
	{
		p[i] = s1[i];
		i++;
	}
	p[i] = '\0';
	return (p);
}

char	*ft_strjoin(char const *s1, char const *s2)
{
	int	i;
	int	j;
	char	*str;

	i = 0;
	j = 0;
	str = (char *)malloc(sizeof(char) * (ft_strlen(s1) + ft_strlen(s2) + 1));
	if (!str)
		return (NULL);
	while (s1[i])
	{
		str[i] = s1[i];
		i++;
	}
	while (s2[j])
	{
		str[i + j] = s2[j];
		j++;
	}
	str[i + j] = '\0';
	return (str)
}